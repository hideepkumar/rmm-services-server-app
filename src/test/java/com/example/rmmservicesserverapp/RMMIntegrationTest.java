package com.example.rmmservicesserverapp;

import com.example.rmmservicesserverapp.model.Device;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = RmmServicesServerAppApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource (locations = "classpath:application.properties")
public class RMMIntegrationTest {

    /**
     * [CURL commands to test the REST endpoint outside of work-environment]
     * curl --data "username=ninja&password=Ninja" --cookie-jar cookie -X POST http://localhost:8090/processLogin
     * curl --cookie cookie -u ninja:Ninja -X GET http://localhost:8090/devices/ninja
     *
     *
     */

    private static String USER = "ninja";

    private enum SERVICE {
        Antivirus, Cloudberry, PSA, Teamviewer;
    }

    @Autowired
    MockMvc mockMvc;

    Device []devices = new Device[]{
            new Device("deviceID_001", "SystemName_001", "Windows Server"),
            new Device("deviceID_002", "SystemName_002", "Windows Workstation"),
            new Device("deviceID_003", "SystemName_003", "Mac"),
            new Device("deviceID_004", "SystemName_004", "Mac"),
            new Device("deviceID_005", "SystemName_005", "Mac")
    };

    @WithMockUser("USER")
    @Test
    public void testAddService() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/"+USER+"/services/" + SERVICE.Teamviewer.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @WithMockUser("USER")
    @Test
    public void testDeleteService() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/deleteService/"+USER+"/" + SERVICE.Cloudberry.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @WithMockUser("USER")
    @Test
    public void testAddDevice() throws Exception {
        for(Device device: devices) {
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/users/"+USER+"/devices").contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(device)))
                    .andExpect(status().isOk());
        }
    }

    @WithMockUser("USER")
    @Test
    public void testGetDevice() throws Exception {
        Device device = devices[2];
        mockMvc.perform(
                MockMvcRequestBuilders.get("/device/" + device.getDeviceId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(device)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId", Matchers.is(device.getDeviceId())))
        ;
    }

    @WithMockUser("USER")
    @Test
    public void testMonthlyBill() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/monthlyBill/" + USER).contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("Monthly bill : $" + result.getResponse().getContentAsString());
    }

    @WithMockUser("USER")
    @Test
    public void testDeleteDevice() throws Exception {
        Device DELETE_DEVICE = devices[3];
        mockMvc.perform(
                MockMvcRequestBuilders.post("/deleteDevice/" +USER+ "/" + DELETE_DEVICE.getDeviceId()).contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(DELETE_DEVICE)))
                    .andExpect(status().isOk());
    }



}
