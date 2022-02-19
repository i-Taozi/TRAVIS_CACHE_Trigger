/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.metrics.bean;

import java.util.Map;

public class MetricSearch {

    private String key;
    private Map<String, String> tags;

    public MetricSearch(){

    }

    public MetricSearch(String key, Map<String, String> tags){
    	this.key = key;
    	this.tags = tags;
    }
	public String getKey() {
		return key;
	}
	public Map<String, String> getTags() {
		return tags;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}


}
