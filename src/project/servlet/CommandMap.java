package project.servlet;

import project.command.ActionCommand;
import project.command.admin.*;
import project.command.authentication.LoginCommand;
import project.command.authentication.LogoutCommand;
import project.command.common.*;
import project.command.subscriber.ConfirmPaymentCommand;
import project.command.subscriber.CreateAccountCommand;
import project.command.subscriber.MakeOrderCommand;
import project.command.subscriber.ShowOrdersCommand;

/**
 * This enumeration denotes pairs in format (url, handle command).
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public enum CommandMap {
    CREATE_ACCOUNT("/register", new CreateAccountCommand()),
    LOGIN("/login", new LoginCommand()),
    LOGOUT("/logout", new LogoutCommand()),
    SUBSCRIBERS("/subscribers", new SubscribersListCommand()),
    SUBSCRIBER("/subscriber", new SubscriberDetailsCommand()),
    SERVICES("/services", new ServiceListCommand()),
    SERVICE("/service", new ServiceDetailsCommand()),
    PAYMENTS("/payments", new PaymentListCommand()),
    PAYMENT("/payment", new PaymentDetailsCommand()),
    ORDERS("/orders", new ShowOrdersCommand()),
    CONFIRM_PAYMENT("/confirmPayment", new ConfirmPaymentCommand()),
    CREATE_PAYMENTS("/createPayments", new CreatePaymentsCommand()),
    CREATE_SERVICE("/createService", new CreateServiceCommand()),
    DEACTIVATE_SERVICE("/deactivateService", new DeactivateServiceCommand()),
    UPDATE_SERVICE("/updateService", new UpdateServiceCommand()),
    ORDER_SERVICE("/orderService", new MakeOrderCommand()),
    BLOCK_USER("/blockUser", new BlockSubscriberCommand()),
    ACTIVATE_USER("/activateUser", new ActivateSubscriberCommand()),
    CHANGE_LANGUAGE("/changeLanguage", new ChangeLanguageCommand());

    String path;
    ActionCommand actionCommand;

    /**
     * @param path path from request
     * @param command request handler
     */
    private CommandMap(String path, ActionCommand command) {
        this.path = path;
        this.actionCommand = command;
    }

    /**
     * Searches for command handler for specified URL
     * @param url
     * @return ActionCommand
     */
    public static ActionCommand getCommand(String url) {
        for (CommandMap commandMap: CommandMap.values()) {
            if (commandMap.path.equals(url)) {
                return commandMap.actionCommand;
            }
        }
        throw new Error("Command can't be found." + url);
    }
}
