package com.example.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String first, last, email;
}
