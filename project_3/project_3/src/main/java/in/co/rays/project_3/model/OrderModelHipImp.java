package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.OrderDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class OrderModelHipImp implements OrderModelInt {

	@Override
	public long add(OrderDto dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();

		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);
			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in Supplier Add " + e.getMessage());
		} finally {
			session.close();
		}
//					/ log.debug("Model add End"); /
		return dto.getId();

	}

	@Override
	public void delete(OrderDto dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Supplier Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(OrderDto dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
//						BankDto existDto = findByLogin(dto.getLogin());
//						// Check if updated LoginId already exist
//						if (existDto != null && existDto.getId() != dto.getId()) {
//							throw new DuplicateRecordException("LoginId is already exist");
//						}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Supplier update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public OrderDto findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		OrderDto dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (OrderDto) session.get(OrderDto.class, pk);
		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;

	}

	@Override
	public OrderDto findByLogin(String login) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		OrderDto dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(OrderDto.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (OrderDto) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting order by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List search(OrderDto dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		// System.out.println(
		// "hellllo" + pageNo + "....." + pageSize + "........" + dto.getId() + "......"
		// + dto.getRoleId());
		Session session = null;
		ArrayList<OrderDto> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(OrderDto.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}

			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<OrderDto>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in order search");
		} finally {
			session.close();
		}

		return list;
	}

}
