package ar.edu.unq.agiletutor.service

import ar.edu.unq.agiletutor.model.Course
import ar.edu.unq.agiletutor.model.Student
import ar.edu.unq.agiletutor.model.Tutor
import ar.edu.unq.agiletutor.persistence.StudentRepository
import org.springframework.beans.factory.annotation.Autowired

data class TutorLoginDTO(
    var email: String,
    var password: String
)

data class TutorRegisterDTO(
    var id: Int?,
    var name: String?,
    var surname: String?,
    var email: String?,
    var password: String?
) {
    fun aModelo(): Tutor {
        val tutor = Tutor()
        tutor.id = id
        tutor.name = name
        tutor.surname = surname
        tutor.email = email
        // tutor.courses = courses!!.map {  CourseDTO (it.id,it.name).aModelo() }.toMutableSet()
        tutor.password = password
        return tutor
    }
}

data class TutorDTO(
    var id: Int?,
    var name: String?,
    var surname: String?,
    var email: String?
    //var courses: List<CourseDTO>?,

) {

    companion object {
        fun desdeModelo(tutor: Tutor): TutorDTO {
            //  val coursesDTO = tutor.courses.map { CourseDTO.desdeModelo(it) }

            return TutorDTO(
                tutor.id,
                tutor.name,
                tutor.surname,
                tutor.email
                //    coursesDTO
            )
        }
    }

    fun aModelo(): Tutor {
        val tutor = Tutor()
        tutor.id = id
        tutor.name = name
        tutor.surname = surname
        tutor.email = email
        // tutor.courses = courses!!.map {  CourseDTO (it.id,it.name).aModelo() }.toMutableSet()
        return tutor
    }
}

data class CourseRegisterDTO(

    var id: Int?,
    var name: String?,
    var tutorId: Int

    )

{
    @Autowired
    private lateinit var tutorService: TutorService

    companion object {
        fun desdeModelo(course: Course): CourseDTO {
            return CourseDTO(course.id, course.name)
        }
    }

    fun aModelo(): Course {
        val course = Course()
        course.id = id
        course.name = name
        course.students = mutableSetOf()
        course.tutor = tutorService.findByID(tutorId)
        return course
    }
}



data class CourseDTO(
    var id: Int?,
    var name: String?,

    ) {
    companion object {
        fun desdeModelo(course: Course): CourseDTO {
            return CourseDTO(course.id, course.name)
        }
    }

    fun aModelo(): Course {
        val course = Course()
        course.id = id
        course.name = name
        return course
    }
}

