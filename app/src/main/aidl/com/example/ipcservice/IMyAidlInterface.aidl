// IMyAidlInterface.aidl
package com.example.ipcservice;
import com.example.ipcservice.People;
// Declare any non-default types here with import statements

interface IMyAidlInterface {

    void sayHi(String content);

    String getName();

    int getAge();

    People who();

    List<People> getStudentList();
}
