package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.OrderDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface OrderModelInt {

	public long add(OrderDto dto) throws ApplicationException, DuplicateRecordException;

	// public long registerUser(BankDto dto) throws ApplicationException,
	// DuplicateRecordException;

	public void delete(OrderDto dto) throws ApplicationException;

	public void update(OrderDto dto) throws ApplicationException, DuplicateRecordException;

	public OrderDto findByPK(long pk) throws ApplicationException;

	public OrderDto findByLogin(String login) throws ApplicationException;

	public List search(OrderDto dto, int pageNo, int pageSize) throws ApplicationException;

}
