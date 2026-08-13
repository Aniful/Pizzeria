package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientGUI {
    private ClientService clientService;
    private OrderService orderService;
    private PizzaService pizzaService;
    private PaymentService paymentService;
    private Scanner scanner;
    private Client currentClient;

    public ClientGUI(ClientService clientService, OrderService orderService, PizzaService pizzaService, PaymentService paymentService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.pizzaService = pizzaService;
        this.paymentService = paymentService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        registerOrLogin();

        while (true) {
            System.out.println("_____________________________________________________________");
            System.out.println("Здесть вы можете:");
            System.out.println("1. Сделать заказ");
            System.out.println("2. Мои заказы");
            System.out.println("3. Посмотреть состав пиццы");
            System.out.println("0. Выйти");
            System.out.println("_____________________________________________________________");

            int userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case 1:
                    Long orderId = makeOrder();
                    if (orderId != null) {
                        payForOrder(orderId);
                    }
                    break;
                case 2:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введенного значения не предусмотрено");
            }

        }
    }

    private void registerOrLogin() {
        System.out.println("_____________________________________________________________");
        System.out.println("Вход или регистрация");
        System.out.println("Введите номер телефона без специальных символов:");
        System.out.println("_____________________________________________________________");
        String numberPhone = scanner.next();

        Optional<Client> client = clientService.findByPhone(numberPhone);
        if (client.isEmpty()) {
            registerClient(numberPhone);
        } else {
            currentClient = client.get();
            System.out.println("Добро пожаловать, " + currentClient.getName());
        }
    }

    private void registerClient(String numberPhone) {
        System.out.println("_____________________________________________________________");
        System.out.println("Вы наш новый клиент! Подскажите как к вам обращаться:");
        System.out.println("_____________________________________________________________");

        String name = scanner.next();
        currentClient = clientService.registerClient(name, numberPhone);

        System.out.println("_____________________________________________________________");
        System.out.println("Регистрация прошла успешно!");
    }

    private Long makeOrder() {
        System.out.println("_____________________________________________________________");
        System.out.println("МЕНЮ ПИЦЦЕРИИ.");
        List<Pizza> menu = pizzaService.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + " " + menu.get(i).getDescription());
        }

        System.out.print("Введите номера пицц через запятую (пробелы по желанию):");
        String userChoice = scanner.nextLine();
        System.out.println("_____________________________________________________________");

        userChoice = userChoice.replaceAll("\\s+", "");
        if (userChoice.isEmpty()) {
            System.out.println("Вы не выбрали ни одной пиццы. Вернемся в меню выбора.");
            return null;
        }
        List<String> userChoiceToArray = Arrays.asList( userChoice.split(",") );

        List<Pizza> items = userChoiceToArray.stream()
                .filter(str -> !str.isEmpty())
                .map(item -> menu.get( Integer.parseInt(item) - 1) )
                .collect(Collectors.toList());

        Address address = getAddress();
        Order order = orderService.makeOrder(currentClient.getId(), address, items);
        System.out.println(order.getDescription());
        System.out.println("Создание завершено, вы можете сделить за статусами готовности");
        return order.getId();
    }

    private Address getAddress() {
        List<Address> addressHistory = currentClient.getAddressHistory();
        if (addressHistory.isEmpty()) {
            System.out.println("Не нашли ни одного адреса для доставки, давайте это исправим");
            return addAddress();
        } else {
            System.out.println("Выберите адрес доставки:");
            for (int i = 0; i < addressHistory.size(); i++) {
                System.out.println( (i + 1) + ". " + addressHistory.get(i).getFullAddress());
            }

            System.out.println("0. Добавить новый адрес");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 0) {
                System.out.println("Начнем создание нового адреса");
                 return addAddress();
            } else if (userChoice < 0 || userChoice > addressHistory.size()) {
                System.out.println("Введенного значения не предусмотрено. Попробуйте еще раз");
                return  getAddress();
            }

            int addressIndex = userChoice  - 1;
            return addressHistory.get(addressIndex);
        }
    }

    private Address addAddress() {
        System.out.println("Введите город:");
        String city = scanner.nextLine();
        System.out.println("Введите улицу:");
        String street = scanner.nextLine();
        System.out.println("Введите номер дома:");
        String home = scanner.nextLine();
        System.out.println("Введите номер квартиры:");
        String apartment = scanner.nextLine();

        Address address = clientService.createAddress(city, street, home, apartment);
        clientService.addAddressToClient(currentClient.getId(), address);
        System.out.println("Добавлен адрес: " + address.getFullAddress() + ". Он будет использован в текущем заказе");
        System.out.println("_____________________________________________________________");
        return address;
    }

    private void payForOrder(Long orderId) {
        Payment.PaymentMethod method = choosePaymentMethod();
        Optional<Order> optionalOrder = orderService.findById(orderId);
        if (optionalOrder.isEmpty()){
            System.out.println("При попытке оплаты заказ не найден.");
            return;
        }

        Order order = optionalOrder.get();

        if (order.getStatus() != Order.Status.NEW) {
            System.out.println("Заказ не может быть оплачен. Его статус: " + order.getStatus());
            return;
        }

        Payment payment = paymentService.payForOrder(orderId, order.getTotalPrice(), method);
        System.out.println("_____________________________________________________________");
        System.out.println("Происходит оплата ...");
        paymentService.processPayment(payment, order);
        System.out.println("Заказ №" + orderId + " успешно подтвержден и оплачен.");
        System.out.println("Возвращаемся в главное меню");
    }

    private Payment.PaymentMethod choosePaymentMethod() {
        Payment.PaymentMethod method = null;

        while (method == null) {
            System.out.println("_____________________________________________________________");
            System.out.println("Выберите способ оплаты");
            System.out.println("1. Карта");
            System.out.println("2. СПБ");
            System.out.println("3. Наличные");

            int userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case 1:
                    method = Payment.PaymentMethod.CARD;
                    break;
                case 2:
                    method = Payment.PaymentMethod.SPB;
                    break;
                case 3:
                    method = Payment.PaymentMethod.CASH;
                    break;
                default:
                    System.out.println("Введенного значения не предусмотрено");
            }
        }
        return method;
    }
}
