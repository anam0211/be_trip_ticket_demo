//package com.javaweb.api;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.javaweb.model.BuildingDTO;
//import com.javaweb.model.ErrorResponse;
//import com.javaweb.service.BuildingService;
//
//import CustomException.FieldRequiredException;
//@RestController
//@PropertySource("classpath:application.properties")
//public class BuildingAPI {
//	@Autowired  
//	private BuildingService buildingService;
//	//@Value("${dev.nguyen}")
//	//private String data;
//	@GetMapping(value="/api/building")
//	public List<BuildingDTO> getBuilding(@RequestParam (name="name") String name) {
//		
//		List<BuildingDTO> result=buildingService.findAll(name);
//		
//		
////		String sql = "SELECT * FROM building b where name like '%" + name+ "%'";
////		List<BuildingDTO> result = new ArrayList<>();
////
////		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
////		     Statement stmt = conn.createStatement();
////		     ResultSet rs = stmt.executeQuery(sql)) {
////
////		    while (rs.next()) {
////		        BuildingDTO building = new BuildingDTO();
////		        building.setName(rs.getString("name"));
////		        building.setStreet(rs.getString("street"));
////		        building.setWard(rs.getString("ward"));
////		        building.setNumberOfBasement(rs.getInt("numberofbasement"));
////		        result.add(building);
////		    }
////
////		} catch (SQLException e) {
////		    e.printStackTrace();
////		    System.out.println("Connected database failed...");
////		}
////
//	return result;
//
//	}
//
////	public Object getBuilding(@RequestBody BuildingDTO res) throws FieldRequiredException {
////		try {
////			validate(res);
////		}
////		catch(FieldRequiredException f){
////			ErrorResponse er= new ErrorResponse();
////			er.setError(f.getMessage());
////			List<String> a= new ArrayList<>();
////			a.add("check lại name hoặc ward vì đang bị null");
////			er.setDetail(a);
////			return er;
////		}
////		validate(res);
////		return null;
////	}
////	public void validate(BuildingDTO res) throws FieldRequiredException {
////		if(res.getName()==null || res.getWard()==null) {
////			throw new FieldRequiredException("name and ward is null");
////			
////		}
////	}
////	public List<BuildingDTO> getBuilding(@RequestParam(value="name", required=false) String name,
////							@RequestParam(value="numberOfBasement", required=false) Integer numberOfBasement,
////							@RequestParam(value="ward", required=false) String ward) {
////		List<BuildingDTO> listBuildings = new ArrayList<>();
////        BuildingDTO buildingDTO1 = new BuildingDTO();
////        buildingDTO1.setName("ABC Building");
////        buildingDTO1.setNumberOfBasement(3);
////        buildingDTO1.setWard("Tân Mai");
////
////        BuildingDTO buildingDTO2 = new BuildingDTO();
////        buildingDTO2.setName("ACM Tower");
////        buildingDTO2.setNumberOfBasement(2);
////        buildingDTO2.setWard("Đa Kao");
////
////        listBuildings.add(buildingDTO1);
////        listBuildings.add(buildingDTO2);
////
////        return listBuildings;
////	}
//	
////	@PostMapping(value="/api/building")
////	public void getBuilding2(@RequestBody Map<String,String> params)  {
////		System.out.print("ok");
////	}
//	@DeleteMapping(value="/api/building/{id}")
//	public void deleteBuilding(@PathVariable Integer id) {
//	    System.out.print(id);
//	}
//
//	
//}
