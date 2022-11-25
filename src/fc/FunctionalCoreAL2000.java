package fc;

import db.dao.SubscriberDAO;
import db.entities.SubscribeCardEntity;
import db.entities.SubscriberEntity;

import java.sql.Date;

public class FunctionalCoreAL2000 {
    Facade facade;
    private Client client;
    private BluRayTable blurays;

    public FunctionalCoreAL2000() {
        facade = new Facade();
        blurays = new BluRayTable();
    }

    public QRCode printQRCode(Movie movie) {
        // maj BD : rentals
        return new QRCode(movie);
    }

    public BluRay deliverBluRay(Movie movie) {
        // maj BD : rentals + blurays
        return blurays.getBluRay(movie);
    }

    public CreditCard determineCreditCard(int cardNumber) {
        // lire BD : cards
        return null;
    }

    public SubscribeCard determineSubscribeCard(int CardNumber) {
        // lire BD : subscribeCards + subscribers
        return null;
    }

    public void rechargeSubscribeCard(int amount) {
        assert (client.getClass() == Subscriber.class);
        Subscriber subscriber = (Subscriber) client;
        if (facade.isValidPayment(subscriber.getCreditCard(), amount)) {
            subscriber.getSubscribeCard().credit(amount);
        } else {
            throw new RuntimeException("Impossible payment!");
        }
    }

    public void rechargeSubSubscribeCard(SubscribeCard card, int amount) {
        if (facade.isValidPayment(card.getCreditCard(), amount)) {
            card.credit(amount);
        } else {
            throw new RuntimeException("Impossible payment!");
        }
    }

    public void connection(Card card) {
        if (card.getClass() == SubscribeCard.class) {
            // lire BD
            SubscribeCard subscribeCard = (SubscribeCard) card;
            client = new Subscriber(subscribeCard.getCreditCard(), subscribeCard);
        } else {
            client = new Client((CreditCard) card);
        }
    }

    public void close() {
        client = null;
    }

    public SubscribeCard subscription(String firstName, String lastName, Date dateOfBirth, String email) {
        /*Subscriber subscriber = client.getClass() == Subscriber.class ? (Subscriber) client : null;
        Lecture BD : trouver plus petit card number dispo
        SubscribeCard newCard = new SubscribeCard(0, firstName, lastName, dateOfBirth, email,
                client.creditCard, subscriber
        );
        Subscriber newSubscriber = new Subscriber(client.getCreditCard(), newCard);
        if (subscriber != null) {
            subscriber.addSubscribeCard(newCard);
        }*/
        /*SubscribeCardEntity subscribeCardEntity = new SubscribeCardEntity();
        subscribeCardEntity.setSubscriberCardId(0);
        subscribeCardEntity.setSubscriber(true);
        subscribeCardEntity.setCardholderFirstName(firstName);
        subscribeCardEntity.setCardholderLastName(lastName);
        subscribeCardEntity.setBirthDate(dateOfBirth);
        subscribeCardEntity.setCardBalance(0);*/
        SubscriberEntity subscriberEntity = new SubscriberEntity();
        subscriberEntity.setSubscriberId(666);
        subscriberEntity.setEmail(email);
        subscriberEntity.setCardNumber(0);
        //SubscribeCardDAO subscribeCardDAO = new SubscribeCardDAO();
        //subscribeCardDAO.save(subscribeCardEntity);
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        subscriberDAO.save(subscriberEntity);
        return null;
    }

    @Override
    public String toString() {
        return client != null ? client.toString() : null;
    }
}
