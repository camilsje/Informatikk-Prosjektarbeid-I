package sportsRentals.springboot.restserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import sportsRentals.core.Equipment;
import sportsRentals.core.EquipmentStorage;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { SportsRentalsController.class, SportsRentalsService.class,
SportsRentalsApplication.class })
@WebMvcTest
public class SportsRentalsControllerTest {

    @Autowired
    private SportsRentalsController sportsRentalsController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportsRentalsService sportsRentalsService;

    @Test
    @DisplayName("Tests that context loads successfully")
    public void contextLoads() throws Exception {
        assertThat(sportsRentalsController).isNotNull();
    }

    @Test
    @DisplayName("Verify that equipmentStorage retrieval from service")
    public void equipmentStorageFromService() throws Exception {
        sportsRentalsService.getEquipmentStorage();
        this.mockMvc.perform(get("/springboot/sportsrentals"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("")));
    }

    @Test
    @DisplayName("Test getEquipmentStorage endpoint")
    public void testGetEquipmentStorage() throws Exception {
        EquipmentStorage mockEquipmentStorage = new EquipmentStorage();
        when(sportsRentalsService.getEquipmentStorage()).thenReturn(mockEquipmentStorage);

        mockMvc.perform(MockMvcRequestBuilders.get("/springboot/sportsrentals")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test getAvailableEquipment endpoint")
    public void testGetAvailableEquipment() throws Exception {
        List<Equipment> mockAllAvailableEquipment = new ArrayList<>();
        when(sportsRentalsService.getAvailableEquipment("2024-05-11", "3")).thenReturn(mockAllAvailableEquipment);

        mockMvc.perform(MockMvcRequestBuilders.get("/springboot/sportsrentals/equipment/available/2024-05-11/3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test getSelectedEquipment endpoint")
    public void testGetSelectedEquipment() throws Exception {
        Equipment mockEquipment = new Equipment("Basketball", null);
        when(sportsRentalsService.getEquipment("Basketball")).thenReturn(mockEquipment);

        mockMvc.perform(MockMvcRequestBuilders.get("/springboot/sportsrentals/equipment/Baksetball")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test postToAvailability endpoint")
    public void testPostToAvailability() throws Exception {
        String body = "{\"type\": \"Bicycle\", \"startdate\": \"2024-03-02\", \"days\": \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/springboot/sportsrentals/equipment/available")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
