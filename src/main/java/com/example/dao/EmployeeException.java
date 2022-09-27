package com.example.dao;

public class EmployeeException extends Exception{
//  private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	public EmployeeException(String message, Throwable cause) {
      super(message, cause);

  }

  public EmployeeException(String message) {
      super(message);

  }
}