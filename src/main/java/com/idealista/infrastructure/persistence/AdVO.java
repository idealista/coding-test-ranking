package com.idealista.infrastructure.persistence;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdVO {

  private Integer id;
  private String typology;
  private String description;
  private List<Integer> pictures;
  private Integer houseSize;
  private Integer gardenSize;
  private Integer score;
  private Date irrelevantSince;

}
