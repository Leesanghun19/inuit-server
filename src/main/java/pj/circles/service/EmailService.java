package pj.circles.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pj.circles.domain.Email;
import pj.circles.repository.EmailRepository;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmailRepository emailRepository;

    @Transactional
    public Long save(String email,String code){
        Email email1=new Email(email, code);
        emailRepository.save(email1);
        return email1.getId();
    }
    @Transactional
    public void setCode(String email,String code){

        Email email1=emailRepository.findByEmail(email).get();
        email1.setCode(code);

    }
    public Optional<Email> findByEmailOp(String email){
        return emailRepository.findByEmail(email);
    }
    public Email findByEmail(String email){
        return emailRepository.findByEmail(email).orElseThrow(()->new NullPointerException("없는값입니다"));
    }
    @Transactional
    public void deleteById(Long id){
        emailRepository.deleteById(id);
    }
    @Transactional
    public void isChecked(String email){
        emailRepository.findByEmail(email).get().isChecked();
    }



}
