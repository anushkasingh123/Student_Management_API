package com.Avengers.Student_Management;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class StudentController {
    HashMap<Integer,Student> db=new HashMap<>();

    @GetMapping("/get_student")
    public Student getStudent(@RequestParam("admnNo") int admnNo)
    {
        return db.get(admnNo);
    }

    @GetMapping("get_student_byname")
    public Student getStudent(@RequestParam("name") String name)
    {
        for(Integer ad:db.keySet())
        {
            Student s=db.get(ad);
            String sname=s.getName();
            if(sname.equals(name))
            {
                return s;
            }
        }
        System.out.println("this student does not exist");
        return null;
    }

    @PostMapping("/add_student")
    public String addStudent(@RequestBody Student student)
    {
        int admnNo= student.getAdmnNo();
        db.put(admnNo,student);
        return "Student added successfully";
    }

    @PutMapping("/update_student")
     public String updateStudent(@RequestParam("admnNo") int admnNo,@RequestBody Student student)
     {
         if(db.containsKey(admnNo))
         {
             db.put(admnNo,student);
             return "Student information updated successfully";
         }
         return "Student does not exist";
     }

     @DeleteMapping("/delete_student")
     public String deleteStudent(@RequestParam("admnNo") int admnNo)
     {
         if(db.containsKey(admnNo))
         {
             Student s=db.get(admnNo);
             db.remove(admnNo,s);
             return "Student information deleted successfully";
         }
         return "Student information was already deleted";
     }


}
