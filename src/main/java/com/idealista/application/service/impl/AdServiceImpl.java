package com.idealista.application.service.impl;


import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdServiceImpl implements AdService {

  private AdRepository adRepository;

}
