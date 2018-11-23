package app.project.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.application.assembler.ProjectCreateAssembler;
import app.project.application.dto.ProjectListDto;
import app.project.infrastructure.persistence.hibernate.ProjectHibernateRepository;


@Service
public class ProjectService {

	@Autowired
	ProjectHibernateRepository projectHibernateRepository;
	
	   @Autowired
	   ProjectCreateAssembler projectCreateAssembler;
	   
	   
	
	 public List<ProjectListDto> getAll() throws SQLException{
	        return projectCreateAssembler.toDtoList(projectHibernateRepository.getAll());
	    }
	
}
