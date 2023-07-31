package br.com.bank.wallet.out;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("wallets")
public record WalletEntity(

        @Id
        String id,
        BigDecimal balance
) {
}
