/**
 * Copyright 2019 Anoop Dixith
 * 
 * Released under the MIT License, whose copy can be obtained at http://opensource.org/licenses/MIT
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 */
package com.personal.resume;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.personal.education.Bachelors;
import com.personal.education.Education;
import com.personal.education.Masters;
import com.personal.generic.SoftwareEngineerResumes;

/**
 * This class details all the sections of my resume.
 * 
 * @author Anoop Dixith
 * 
 */
public class LatestCv implements SoftwareEngineerResumes {

  public static Double version = 4.0;
  static Logger logger = Logger.getLogger(LatestCv.class);

  /**
   * 
   * @author anoop This inner class contains contact details of the applicant.
   */
  class ContactDetails {
    private final String FULLNAME = "Anoop Dixith";
    private final String EMAIL = "dixithanoop@gmail.com";
    private final Long DATE_OF_BIRTH = 575539200l; // Unix timestamp (seconds)

    public String getFULLNAME() {
      return FULLNAME;
    }

    public String getEMAIL() {
      return EMAIL;
    }

    public Long getDATEOFBIRTH() {
      return DATE_OF_BIRTH;
    }

    public Date getReadableDATEOFBIRTH() {
      return new Date(DATE_OF_BIRTH * 1000); // Initialization of Date is in ms, timestamp in s.
    }
  }

  /*
   * Education Details.
   */
  // I could move these values to a properties file, but retained for ease of reading.
  private Boolean bachelorsCompleted = true;
  private Double bachelorsAggregate = 73.2;
  private final String BACHELORSBRANCH = "Electronics and Communication";
  Bachelors bachelors = new Bachelors(BACHELORSBRANCH, bachelorsAggregate, bachelorsCompleted);

  private final String MASTERS_BRANCH = "Computer Science";
  private Double gpa = 3.906;
  private String specialization = "Artificial Intelligence";
  Masters masters = new Masters(MASTERS_BRANCH, gpa, specialization);

  private Education mostRecentEducation = masters;

  private Education education = new Education(bachelors, masters, mostRecentEducation);

  public Education getEducation() {
    return education;
  }

  /*
   * Skills and technologies used.
   */
  public enum SkillSet {
    JAVA, SCALA, GOLANG, DATA_ENGINEERING, BIG_DATA, STREAMING_ANALYTICS
  }
  public enum Frameworks {
    KAFKA, APACHE_SPARK, HBASE, HIVE, KSTREAMS, MONGO_DB, KUBERNETES
  }
  public enum ToolsUsed {
    GRADLE, MAVEN, DATABRICKS, JUPYTER
  }

  /*
   * Past work experience.
   */
  private Map<String, String> workExperience = new HashMap<String, String>();

  /**
   * Wrapper that adds the past work experience of the applicant.
   * 
   * @return Hashmap<String, String> object containing the work experience.
   */
  public Map<String, String> getWorkExperience() {
    this.addWorkExperience();
    return workExperience;
  }

  /**
   * Adds the past work experience of the applicant.
   * 
   */
  @Override
  public void addWorkExperience() {
    try {
      Properties professionalProjects = new Properties();
      String projectFile = "projectdetails.properties"; // contains company=projectDetails
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(projectFile);
      professionalProjects.load(inputStream);
      this.workExperience.put("ZYNGA", professionalProjects.getProperty("zynga"));
      this.workExperience.put("Google", professionalProjects.getProperty("google"));
      this.workExperience.put("CONVIVA", professionalProjects.getProperty("conviva"));
      this.workExperience.put("JUNIPER", professionalProjects.getProperty("juniper"));
      this.workExperience.put("WEWORK", professionalProjects.getProperty("wework"));

      this.workExperience.put("YourCompany", "Something Amazing!");

    } catch (IOException ioException) {
      logger.error("Houston, we've a problem.");
      ioException.printStackTrace();
    }

  }

}
