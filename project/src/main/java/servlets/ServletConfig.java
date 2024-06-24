package servlets;

import crud.Lab2CrudInterface;
import crud.FileCrud;

public class ServletConfig implements ServletConfigInterface {

	Lab2CrudInterface I2ci;
	
	public ServletConfig() {
		this.I2ci = new FileCrud();
	}

	public void setI2ci(Lab2CrudInterface i2ci) {
		I2ci = i2ci;
	}




	@Override
	public Lab2CrudInterface getCrud() {
		// TODO Auto-generated method stub
		return I2ci;
	}

}
