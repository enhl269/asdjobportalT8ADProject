package sg.edu.iss.asdadt8;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.review.ReviewController;
import sg.edu.iss.asdadt8.review.ReviewService;

//To connect to project
@SpringBootTest(classes = AsdJavaProjectT8ProjectApplication.class )
//To create ordering amoung test methods
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewControllerTest {
	
	@InjectMocks
	ReviewController rController;
	
	private MockMvc mockMvc;
     
    @Mock
    ReviewService rservice;
    
	
    @Test
	@Order(1)
    public void testCreateReview() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Review r = new Review(2,4f, "best place to workblahblahblah", null, null, null,null);
        ResponseEntity<Review> responseEntity = rController.saveReview(r);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

}
