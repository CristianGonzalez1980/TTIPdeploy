package ar.edu.unq.agiletutor.service


import ar.edu.unq.agiletutor.model.Course
import ar.edu.unq.agiletutor.model.Student
import ar.edu.unq.agiletutor.model.Tutor
import jakarta.annotation.PostConstruct
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InitService {

    protected val logger = LogFactory.getLog(javaClass)

    @Value("\${spring.datasource.driverClassName:NONE}")
    private val className: String? = null

    @Autowired
    private val attendanceService: AttendanceService? = null

    @Autowired
    private val studentService: StudentService? = null

    @Autowired
    private val courseService: CourseService? = null


    @Autowired
    private val tutorService: TutorService? = null


    @PostConstruct
    fun initialize() {

        if (className == "com.mysql.cj.jdbc.Driver") {
            logger.info("Init Data Using Mysql DB")
            fireInitialData()
        }
    }

     private fun fireInitialData() {

         val tutor1 = Tutor(0,"tutor1","ape1","tutor1@gmail.com", "passtut1",mutableSetOf())
         val tutor2 = Tutor(0,"tutor2","ape2","tutor2@gmail.com", "passtut2",mutableSetOf())
        val tutor1saved = tutorService!!.register(tutor1)
        val tutor2saved = tutorService!!.register(tutor2)

         val course1 = Course(0,"c1",mutableSetOf(),tutor1saved)
         val course2= Course(0,"c2",mutableSetOf(),tutor1saved)
         val course3 = Course(0,"c3",mutableSetOf(),tutor2saved)
         val course4= Course(0,"c4",mutableSetOf(),tutor2saved)

         val course1Saved = courseService!!.register(course1)
         val course2Saved = courseService!!.register(course2)
         val course3Saved = courseService!!.register(course3)
         val course4Saved = courseService!!.register(course4)

         val firstattendacesDTO = mutableSetOf<AttendanceDTO>()
         for (i in  (1..6) ){
             firstattendacesDTO.add(AttendanceDTO(0,i,false))
         }

         val atendacesDTO = mutableSetOf<AttendanceDTO>()
         atendacesDTO.add( AttendanceDTO(0,1,true))
         atendacesDTO.add( AttendanceDTO(0,2,true))
         atendacesDTO.add( AttendanceDTO(0,3,false))
         atendacesDTO.add( AttendanceDTO(0,4,true))
         atendacesDTO.add( AttendanceDTO(0,5,false))
         atendacesDTO.add(AttendanceDTO(0,6,false))

        val attendamces =  firstattendacesDTO.map { it.aModelo() }.toMutableSet()
        val attendamces2 =  atendacesDTO.map { it.aModelo() }.toMutableSet()


         val student1 = Student(  0,"Ale","Fariña", "123","ale@gmail.com" , attendamces2,0.0,"" ,course1Saved )
       studentService!!.register(student1)

        val student2= Student( 0,"Cristian", "Gonzalez","456","cristian@gmail.com" ,attendamces2,0.0,"", course1Saved)
        studentService!!.register(student2)

        val student3= Student( 0,"Pedro", "Picapiedra","456","pica@gmail.com" ,attendamces ,0.0,"", course2Saved)
        studentService!!.register(student3)

        val student4= Student( 0,"Pablo", "Marmol","456","marmol@gmail.com" ,attendamces ,0.0,"",course2Saved)
        studentService!!.register(student4)

         val student5= Student( 0,"Alu1", "Marmol","456","alu1@gmail.com" ,attendamces ,0.0,"",course3Saved)
         studentService!!.register(student5)

         val student6= Student( 0,"Alu2", "Marmol","456","alu2@gmail.com" ,attendamces ,0.0,"",course3Saved)
         studentService!!.register(student6)

         val student7= Student( 0,"Alu3", "Marmol","456","alu3@gmail.com" ,attendamces ,0.0,"",course4Saved)
         studentService!!.register(student7)


         val student8= Student( 0,"Alu4", "Marmol","456","alu4@gmail.com" ,attendamces ,0.0,"",course4Saved)
         studentService!!.register(student8)

    }
}