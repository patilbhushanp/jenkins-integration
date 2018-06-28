package com.sanbhu.jenkins.integration.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.offbytwo.jenkins.model.Job;

public interface JenkinsIntegration {
	List<String> getJobList(final String username, final String password);

	Job getJob(String username, String password, String jobName);
	
	boolean executeJob(Job job, Map<String, String> jobParameters);

	InputStream getBuild(String jobName);
}
