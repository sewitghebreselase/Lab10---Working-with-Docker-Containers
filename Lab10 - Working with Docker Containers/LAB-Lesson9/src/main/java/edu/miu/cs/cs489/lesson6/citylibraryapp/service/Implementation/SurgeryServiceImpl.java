package edu.miu.cs.cs489.lesson6.citylibraryapp.service.Implementation;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.SurgeryRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.Surgery;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.ISurgeryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SurgeryServiceImpl implements ISurgeryService {

    @Autowired
    private SurgeryRepository surgeryRepository;

    public Surgery save(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }
}