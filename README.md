ObjectBee
=======
A simple data transporte library

[![License](http://img.shields.io/badge/License-Apache%202-brightgreen.svg?style=flat)](https://github.com/ihsanbal/ObjectBee/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/ihsanbal/ObjectBee.svg?branch=master)](https://travis-ci.org/ihsanbal/ObjectBee)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ObjectBee-green.svg?style=flat)](http://android-arsenal.com/details/1/2041)


Download
--------

Download [the latest AAR][2] or grab via Gradle:
```groovy
repositories {
	    maven {
	        url "https://jitpack.io"
	    }
	}
	
dependencies {
	        compile 'com.github.ihsanbal:ObjectBee:1.0.0'
	}
```
or Maven:
```xml
<repository>
   <id>jitpack.io</id>
   <url>https://jitpack.io</url>
</repository>

<dependency>
   <groupId>com.github.ihsanbal</groupId>
   <artifactId>ObjectBee</artifactId>
   <version>1.0.0</version>
</dependency>
```
TODO
--------
Change object cache algorithm,maybe use a good library
And reload this README file with a proper english :)

Usage
--------
You must to create data model extends BaseBeeModel
```java
package ihsan.bal.library.base;

import java.io.Serializable;

/**
 * Created by ihsan on 23/05/15.
 */
public class BaseBeeModel implements Serializable {

    public boolean deletepullobject;

    public String referencesname;
    /*
    * default ok_response code 100
    * error_response code 99
    * */
    public int responsecode = 100;

    public String responsemessage;

    public BaseBeeModel(String referencesname){
        if (referencesname!=null)
        this.referencesname = referencesname;
    }

}
```
Example Data Model
```java
package ihsan.bal.library.base;

/**
 * Created by ihsan on 23/05/15.
 */
public class BaseModel extends BaseBeeModel {
    public String title;

    public BaseModel() {
        super("BaseModel");
    }
}
```

If you want do save data and start activity
```java
BaseModel model = new BaseModel();
        model.title = "Pushed A model";
Bee.with(this).been(model).pushAndStart(DetailActivity.class);
```

If you want do save data
```java
BaseModel model = new BaseModel();
        model.title = "Pushed A model";
Bee.with(this).been(model).push();
```
And get the data (Will contribute)
```java
Bee.with(this).been(BaseModel.class).pull();
```
And get the data with tag (Will contribute)
```java
Bee.with(this).been(BaseModel.class).pull("taggedObjectViewModel");
```
And get the data and delete on catche (Will contribute)
```java
Bee.with(this).been(BaseModel.class).pull(true);
```
And get the data with tag and delete on catche (Will contribute)
```java
Bee.with(this).been(BaseModel.class).pull(true,"taggedObjectViewModel");
```


Licence
--------------
Copyright [2015] [İHSAN BAL]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Author
--------------
[İHSAN BAL](https://github.com/ihsanbal)
