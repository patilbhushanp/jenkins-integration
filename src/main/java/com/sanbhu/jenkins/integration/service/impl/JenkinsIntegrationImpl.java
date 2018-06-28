package com.sanbhu.jenkins.integration.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.sanbhu.jenkins.integration.constants.ApplicationConstant;
import com.sanbhu.jenkins.integration.service.JenkinsIntegration;

public class JenkinsIntegrationImpl implements JenkinsIntegration {

	@Override
	public List<String> getJobList(String username, String password) {
		final List<String> jobList = new ArrayList<String>();
		final JenkinsServer jenkinsServer = performAuthentication(username, password);
		try {
			Map<String, Job> jobs = jenkinsServer.getJobs();
			for (String jobName : jobs.keySet()) {
				jobList.add(jobName);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return jobList;
	}

	@Override
	public Job getJob(String username, String password, String jobName) {
		Job job = null;
		final JenkinsServer jenkinsServer = performAuthentication(username, password);
		try {
			job = jenkinsServer.getJob(jobName);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return job;
	}

	@Override
	public boolean executeJob(Job job, Map<String, String> jobParameters) {
		boolean executeResult = false;
		try {
			if(jobParameters.isEmpty()) {
				job.build();
			} else {
				job.build(jobParameters);	
			}
			executeResult = true;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return executeResult;
	}

	public InputStream getBuild(String jobName) {
		return null;
	}

	private JenkinsServer performAuthentication(final String username, final String password) {
		JenkinsServer jenkinsServer = null;
		try {
			jenkinsServer = new JenkinsServer(new URI(ApplicationConstant.URL), username, password);
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
		}
		return jenkinsServer;
	}

}
