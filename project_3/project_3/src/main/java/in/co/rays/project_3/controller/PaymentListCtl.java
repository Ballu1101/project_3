package in.co.rays.project_3.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PaymentModelInt;
import in.co.rays.project_3.model.UserModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "PaymentListCtl", urlPatterns = { "/ctl/PaymentListCtl" })



public class PaymentListCtl extends BaseCtl {
	
	
		private static final long serialVersionUID = 1L;
		private static Logger log = Logger.getLogger(BankListCtl.class);

		/*
		 * protected void preload(HttpServletRequest request) { RoleModelInt model =
		 * ModelFactory.getInstance().getRoleModel(); try { List list = model.list();
		 * request.setAttribute("roleList", list); } catch (Exception e) { log.error(e);
		 * 
		 * } }
		 */

		
		@Override
		protected void preload(HttpServletRequest request) {
		PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
		try {
		List list =	model.list();
		request.setAttribute("typeList", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		
		@Override
		protected BaseDTO populateDTO(HttpServletRequest request) {
			PaymentDTO dto = new PaymentDTO();

			dto.setType(DataUtility.getString(request.getParameter("type")));

			dto.setTransactionId(DataUtility.getString(request.getParameter("transactionId")));

			populateBean(dto, request);
			return dto;
		}

		/**
		 * Contains Display logics
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			log.debug("paymentListCtl doGet Start");
			List list;
			List next;
			int pageNo = 1;
			int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
			System.out.println("==========" + pageSize);
			PaymentDTO dto = (PaymentDTO) populateDTO(request);
	// get the selected checkbox ids array for delete list
			PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
			try {
				System.out.println("in ctllllllllll search");
				list = model.search(dto, pageNo, pageSize);

				ArrayList<PaymentDTO> a = (ArrayList<PaymentDTO>) list;

				for (PaymentDTO udto1 : a) {
					System.out.println();
				}

				System.out.println(list + "----------------------------------------------------------");
				System.out.println(list.indexOf(3));
				next = model.search(dto, pageNo + 1, pageSize);
				ServletUtility.setList(list, request);
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
				if (next == null || next.size() == 0) {
					request.setAttribute("nextListSize", 0);

				} else {
					request.setAttribute("nextListSize", next.size());
				}
				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (Exception e) {
	// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("paymentListCtl doPOst End");
		}

		/**
		 * Contains Submit logics
		 */
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			log.debug("paymentListCtl doPost Start");
			List list = null;
			List next = null;
			int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

			pageNo = (pageNo == 0) ? 1 : pageNo;
			pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
			PaymentDTO dto = (PaymentDTO) populateDTO(request);
			String op = DataUtility.getString(request.getParameter("operation"));
			System.out.println("op---->" + op);

	// get the selected checkbox ids array for delete list
			String[] ids = request.getParameterValues("ids");
			PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
			try {

				if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

					if (OP_SEARCH.equalsIgnoreCase(op)) {
						pageNo = 1;
					} else if (OP_NEXT.equalsIgnoreCase(op)) {
						pageNo++;
					} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
						pageNo--;
					}

				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.PAYMENT_CTL, request, response);
					return;
				} else if (OP_RESET.equalsIgnoreCase(op)) {

					ServletUtility.redirect(ORSView.PAYMENT_LIST_CTL, request, response);
					return;
				} else if (OP_DELETE.equalsIgnoreCase(op)) {
					pageNo = 1;
					if (ids != null && ids.length > 0) {
						PaymentDTO deletedto = new PaymentDTO();
						for (String id : ids) {
							deletedto.setId(DataUtility.getLong(id));
							model.delete(deletedto);
							ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
						}
					} else {
						ServletUtility.setErrorMessage("Select at least one record", request);
					}
				}
				if (OP_BACK.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.PAYMENT_LIST_CTL, request, response);
					return;
				}
				dto = (PaymentDTO) populateDTO(request);

				list = model.search(dto, pageNo, pageSize);

				ServletUtility.setDto(dto, request);
				next = model.search(dto, pageNo + 1, pageSize);

				ServletUtility.setList(list, request);
				ServletUtility.setList(list, request);
				if (list == null || list.size() == 0) {
					if (!OP_DELETE.equalsIgnoreCase(op)) {
						ServletUtility.setErrorMessage("No record found ", request);
					}
				}
				if (next == null || next.size() == 0) {
					request.setAttribute("nextListSize", 0);

				} else {
					request.setAttribute("nextListSize", next.size());
				}
				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (Exception e) {
	// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("paymentListCtl doGet End");
		}

		@Override
		protected String getView() {
			return ORSView.PAYMENT_LIST_VIEW;
		}

	}


