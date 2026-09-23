package de.atruvia.webapp.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MailServiceDummy {

    private final String username;
    private final String password;



    public void send(final String subject, final String text) {
        System.out.println( "MailServiceDummy: " + subject + " " + text);
        System.out.println(this);
    }
}
