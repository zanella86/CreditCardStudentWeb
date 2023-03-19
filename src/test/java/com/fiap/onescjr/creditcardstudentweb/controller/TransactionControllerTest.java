package com.fiap.onescjr.creditcardstudentweb.controller;

import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testPost() throws Exception {
       // mvc.perform(get("/transaction")).andExpect(status().isOk());
    }

 /*   @Test
    void testPatch() {
        when(transactionService.update(anyLong(), any())).thenReturn(null);

        ResponseEntity<Object> result = transactionController.patch(Long.valueOf(1), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGet() {
        when(transactionService.get(anyLong())).thenReturn(null);

        ResponseEntity<TransactionDTO> result = transactionController.get(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testList() {
        when(transactionService.list(anyLong(), any(), any())).thenReturn(List.of(null));

        ResponseEntity<Object> result = transactionController.list(LocalDateTime.of(2023, Month.MARCH, 19, 17, 6, 32), LocalDateTime.of(2023, Month.MARCH, 19, 17, 6, 32), Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testRemove() {
        transactionController.remove(Long.valueOf(1));
    }*/
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme