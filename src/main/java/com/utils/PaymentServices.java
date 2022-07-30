package com.utils;

import com.DAO.OrderDetailDAO;
import com.DTO.OrderDTO;
import com.DTO.OrderDetailDTO;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {
    private static final String CLIENT_ID = "Your_PayPal_Client_ID";
    private static final String CLIENT_SECRET = "Your_PayPal_Client_Secret";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDTO order)
            throws PayPalRESTException, SQLException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(order);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private String getApprovalLink(Payment approvedPayment) {
        return null;
    }

    private List<Transaction> getTransactionInformation(OrderDTO order) throws SQLException {
        Amount amount = new Amount();
        amount.setCurrency("VND");
        amount.setTotal(Float.toString(order.getTotal()));
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        String transactionDescription = "";

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<OrderDetailDTO> orderDetail = orderDetailDAO.getByOrderID(order.getOrderID());
        for (OrderDetailDTO detail: orderDetail) {

        }
        return null;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/Pets_Paradise_war_exploded/cancelPaypal.jsp");
        redirectUrls.setReturnUrl("http://localhost:8080/Pets_Paradise_war_exploded/reviewPaypalPayment");
        return null;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Paradise").setLastName("Pets").setEmail("thisisthientesting@gmail.com");
        payer.setPayerInfo(payerInfo);
        return payer;
    }
}
