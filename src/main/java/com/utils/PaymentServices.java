package com.utils;

import com.DAO.OrderDetailDAO;
import com.DTO.OrderDTO;
import com.DTO.OrderDetailDTO;
import com.checkout.ItemDetails;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {
    private static final String CLIENT_ID = "AZflhui6Q64uPi3EXjrbhk9IZAps7A2bgzosuQGl8aWWCPUmYOOwSp1NIOUDWThA8z5r3H16qk6mCeNZ";
    private static final String CLIENT_SECRET = "EMgk94-o_Ilu0nWSEppjmbptV3o2-oNnGTRxc29WMMgghCRecmoS12kDWRUioO1574lQlDNYnqx3vmfZ";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDTO order, List<ItemDetails> list)
            throws PayPalRESTException, SQLException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(order, list);

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
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link: links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }
        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDTO order, List<ItemDetails> list) throws SQLException {
        Amount amount = new Amount();
        amount.setCurrency("VND");
        amount.setTotal(Float.toString(order.getTotal()));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        String transactionDescription = "";
        for(ItemDetails details: list) {
            transactionDescription += details.getName() + ",";
        }
        transaction.setDescription(transactionDescription);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        for (ItemDetails details: list) {
            Item item = new Item();
            item.setCurrency("VND");
            item.setName(details.getName());
            item.setPrice(Float.toString(details.getPrice()));
            item.setQuantity(Integer.toString(details.getQuantity()));
            items.add(item);
        }
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
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
