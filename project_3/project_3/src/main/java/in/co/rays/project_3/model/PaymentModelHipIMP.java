package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PaymentModelHipIMP implements PaymentModelInt {

	@Override
	public long add(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {

//				System.out.println("in add");
//				// TODO Auto-generated method stub
//				/ log.debug("usermodel hib start"); /
		//
//				BankDto existDto = null;
//				existDto = findByLogin(dto.getLogin());
//				if (existDto != null) {
//					throw new DuplicateRecordException("login id already exist");
//				}
		Session session = HibDataSource.getSession();

		/*
		 * SessionFactory sf = new Configuration().configure().buildSessionFactory();
		 * Session session = sf.openSession();
		 */
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
			throw new ApplicationException("Exception in payment Add " + e.getMessage());
		} finally {
			session.close();
		}
//				/ log.debug("Model add End"); /
		return dto.getId();

	}

	@Override
	public void delete(PaymentDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in payment Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
//					BankDto existDto = findByLogin(dto.getLogin());
//					// Check if updated LoginId already exist
//					if (existDto != null && existDto.getId() != dto.getId()) {
//						throw new DuplicateRecordException("LoginId is already exist");
//					}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in payment update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public PaymentDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PaymentDTO) session.get(PaymentDTO.class, pk);
		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting payment by pk");
		} finally {
			session.close();
		}

		return dto;

	}

	@Override
	public PaymentDTO findByLogin(String login) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (PaymentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Bank by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List search(PaymentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		// System.out.println(
		// "hellllo" + pageNo + "....." + pageSize + "........" + dto.getId() + "......"
		// + dto.getRoleId());
		Session session = null;
		ArrayList<PaymentDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getType() != null && dto.getType().length() > 0) {
					criteria.add(Restrictions.like("type", dto.getType() + "%"));
				}
//					if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
//						criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
//					}
				//
//					if (dto.getLastName() != null && dto.getLastName().length() > 0) {
//						criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
//					}
//					if (dto.getLogin() != null && dto.getLogin().length() > 0) {
//						criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
//					}
//					if (dto.getPassword() != null && dto.getPassword().length() > 0) {
//						criteria.add(Restrictions.like("password", dto.getPassword() + "%"));
//					}
//					if (dto.getGender() != null && dto.getGender().length() > 0) {
//						criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
//					}
//					if (dto.getDob() != null && dto.getDob().getDate() > 0) {
//						criteria.add(Restrictions.eq("dob", dto.getDob()));
//					}
//					if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
//						criteria.add(Restrictions.eq("lastLogin", dto.getLastLogin()));
//					}
//					if (dto.getRoleId() > 0) {
//						criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
//					}
//					if (dto.getUnSuccessfullLogin() > 0) {
//						criteria.add(Restrictions.eq("unSuccessfulLogin", dto.getUnSuccessfullLogin()));
//					}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<PaymentDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in payment search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Users list");
		} finally {
			session.close();

		}

		return list;

	}

}
