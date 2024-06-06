package in.co.rays.project_3.model;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.SupplierDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;


public class SupplierModelHipImp implements SupplierModelInt {
	
	
	
		@Override
		public long add(SupplierDTO dto) throws ApplicationException, DuplicateRecordException {

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
				throw new ApplicationException("Exception in Supplier Add " + e.getMessage());
			} finally {
				session.close();
			}
//				/ log.debug("Model add End"); /
			return dto.getId();

		}

		@Override
		public void delete(SupplierDTO dto) throws ApplicationException {
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
		public void update(SupplierDTO dto) throws ApplicationException, DuplicateRecordException {
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
				throw new ApplicationException("Exception in Supplier update" + e.getMessage());
			} finally {
				session.close();
			}

		}

		@Override
		public SupplierDTO findByPK(long pk) throws ApplicationException {
			// TODO Auto-generated method stub
			Session session = null;
			SupplierDTO dto = null;
			try {
				session = HibDataSource.getSession();
				dto = (SupplierDTO) session.get(SupplierDTO.class, pk);
			} catch (HibernateException e) {
				throw new ApplicationException("Exception : Exception in getting Bank by pk");
			} finally {
				session.close();
			}

			return dto;

		}

		@Override
		public SupplierDTO findByLogin(String login) throws ApplicationException {
			// TODO Auto-generated method stub
			Session session = null;
			SupplierDTO dto = null;
			try {
				session = HibDataSource.getSession();
				Criteria criteria = session.createCriteria(SupplierDTO.class);
				criteria.add(Restrictions.eq("login", login));
				List list = criteria.list();
				if (list.size() == 1) {
					dto = (SupplierDTO) list.get(0);
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
		public List search(SupplierDTO dto, int pageNo, int pageSize) throws ApplicationException {
			// TODO Auto-generated method stub

			// System.out.println(
			// "hellllo" + pageNo + "....." + pageSize + "........" + dto.getId() + "......"
			// + dto.getRoleId());
			Session session = null;
			ArrayList<SupplierDTO> list = null;
			try {
				session = HibDataSource.getSession();
				Criteria criteria = session.createCriteria(SupplierDTO.class);
				if (dto != null) {
					if (dto.getId() != null) {
						criteria.add(Restrictions.like("id", dto.getId()));
					}
					
					if(dto.getName() !=null && dto.getName().length() > 0) {
						criteria.add(Restrictions.like("name", dto.getName()));
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
				list = (ArrayList<SupplierDTO>) criteria.list();
			} catch (HibernateException e) {
				throw new ApplicationException("Exception in bank search");
			} finally {
				session.close();
			}

			return list;
		}

	}



