package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.BankDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface BankModelInt {
	public long add(BankDTO dto) throws ApplicationException, DuplicateRecordException;

	//public long registerUser(BankDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(BankDTO dto) throws ApplicationException;

	public void update(BankDTO dto) throws ApplicationException, DuplicateRecordException;

	public BankDTO findByPK(long pk) throws ApplicationException;

	public BankDTO findByLogin(String login) throws ApplicationException;

	// public UserDTO authenticate(String login, String password) throws
	// ApplicationException;

	// public List list() throws ApplicationException;

//	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(BankDTO dto, int pageNo, int pageSize) throws ApplicationException;

	// public List search(UserDTO dto) throws ApplicationException;

	// public List getRoles(UserDTO dto) throws ApplicationException;

}
