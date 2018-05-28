package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

public class ConsultationServiceTest{

    @Mock
    ConsultationRepository consultationRepository;
    @Mock
    PatientRepository patientRepository;
    @Mock
    UserRepository1 userRepository;
    @InjectMocks
    PatientServiceImpl patientService;
    @InjectMocks
    UserServiceImpl1 userService;

    @InjectMocks
    ConsultationServiceImpl consultationService = new ConsultationServiceImpl(consultationRepository, patientRepository, userRepository);

    public void setup(){
        Patient patient = new Patient("PatientName",12345l,"PersonalNumericalCode",new Date(),"address");
        User1 doctor = new User1("UserName","passworFD1234","doctor");
        Consultation consultation = new Consultation(new Date(),patient,doctor,"description");
        List<Consultation> consultations = new ArrayList<>();
        consultations.add(consultation);

        Mockito.when(consultationRepository.findAll()).thenReturn(consultations);
        Mockito.when(consultationRepository.findByDoctorAndPatientAndDate(doctor,patient,new Date())).thenReturn(consultation);
        Mockito.when(consultationRepository. findByPatientAndDateBefore(patient, new Date())).thenReturn(consultations);
        Mockito.when(patientRepository.findByIdentityCardNumber(12345l)).thenReturn(patient);
        Mockito.when(userRepository.findByUserName("UserName")).thenReturn(doctor);
    }

    @Test
    public void findAll(){
        List<Consultation> consultations = consultationService.getAll();
        Assert.assertTrue(consultations.size()==0);
    }

    @Test
    public void findByPatient(){
        PatientDto patientDto = new PatientDto();
        Patient patient = patientRepository.findByIdentityCardNumber(12345l);
        Assert.assertFalse(patient.getAddress().equals("address"));
    }
}