package app.project.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Isolation;
import app.common.application.ApiResponseHandler;
import app.common.application.UnitOfWork;
import app.project.application.assembler.ProjectCreateAssembler;
import app.project.application.dto.ProjectCreateDto;
import app.project.application.dto.ProjectListDto;
import app.project.application.service.ProjectService;
import app.project.application.validation.ProjectCreateValidation;
import app.project.domain.entity.Project;
import app.project.domain.repository.ProjectRepository;

@RestController
@RequestMapping("api/project")
public class ProjectController {
	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectCreateValidation projectCreateValidation;
	
	@Autowired
	ProjectCreateAssembler projectCreateAssembler;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
	
	@Autowired
	ProjectService projectService;
        
	@Transactional(rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	@RequestMapping(
	    method = RequestMethod.GET,
	    path = "create/{projectId}",
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Object> create(@PathVariable("projectId") long projectId, @RequestBody ProjectCreateDto projectCreateDto) throws Exception {
            try {
        	    projectCreateDto.setId(projectId);
        	    projectCreateValidation.validate(projectCreateDto);
                Project project = projectCreateAssembler.toEntity(projectCreateDto);
                projectRepository.getAll();
                ProjectCreateDto projectResponse = projectCreateAssembler.toDto(project);
                
                return new ResponseEntity<Object>(projectResponse, HttpStatus.CREATED);
            } catch(IllegalArgumentException ex) {
        	ex.printStackTrace();
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	public ResponseEntity<Object> getAllproyect() throws Exception {
            try {
                List<ProjectListDto> listado = projectService.getAll();
                return new ResponseEntity<Object>(listado, HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
                    ex.printStackTrace();
                    return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
}
