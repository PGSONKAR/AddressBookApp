package com.example.addressbook;

import com.example.addressbook.controller.AddressBookController;
import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.dto.ResponseDTO;
import com.example.addressbook.exception.AddressBookNotFoundException;
import com.example.addressbook.interfaces.IAddressBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private IAddressBookService addressBookService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMyAllAddressBook() {
        List<AddressBookDTO> addressBookList = new ArrayList<>();
        addressBookList.add(new AddressBookDTO());

        when(addressBookService.getMyAddressBookData(any(HttpServletRequest.class)))
                .thenReturn(addressBookList);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.getMyAllAddressBook(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Get All Address Book Data", response.getBody().getMessage());
    }

    @Test
    void getContactById() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setId(1L);

        when(addressBookService.getAddressBookDataById(any(HttpServletRequest.class), anyLong()))
                .thenReturn(addressBookDTO);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.getContactById(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Get Call for ID Successful", response.getBody().getMessage());
    }

    @Test
    void addInAddressBook() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();

        when(addressBookService.createAddressBookData(any(AddressBookDTO.class)))
                .thenReturn(addressBookDTO);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.addInAddressBook(addressBookDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Create New Address Book Data", response.getBody().getMessage());
    }

    @Test
    void updateAddressBook() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setId(1L);

        when(addressBookService.getAddressBookDataById(any(HttpServletRequest.class), anyLong()))
                .thenReturn(addressBookDTO);
        when(addressBookService.updateAddressBookData(anyLong(), any(AddressBookDTO.class)))
                .thenReturn(true);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.updateAddressBook(1L, addressBookDTO, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Address Book Data for:" + addressBookDTO + "to below Data ", response.getBody().getMessage());
    }

    @Test
    void deleteAddressBook() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setId(1L);

        when(addressBookService.getAddressBookDataById(any(HttpServletRequest.class), anyLong()))
                .thenReturn(addressBookDTO);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.deleteAddressBook(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted Address Book Data for id: 1", response.getBody().getMessage());
    }

    @Test
    void getAllAddressBook() {
        List<AddressBookDTO> addressBookList = new ArrayList<>();
        addressBookList.add(new AddressBookDTO());

        when(addressBookService.getAllAddressBookData())
                .thenReturn(addressBookList);

        ResponseEntity<ResponseDTO<?>> response = addressBookController.getAllAddressBook();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Get All Address Book Data", response.getBody().getMessage());
    }
}