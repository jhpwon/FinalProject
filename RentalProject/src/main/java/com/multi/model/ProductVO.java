package com.multi.model;

import lombok.Data;

@Data
public class ProductVO {
	
	//property
		private String upCg_code;
		private String upCg_name;
		private String downCg_code;
		private String downCg_name;
		
		private int pnum;
		private String pname;
		private String pimage1;
		private String pimage2;
		private String pimage3;
		
		private int price;
		private int saleprice;
		private int point;
		private int pqty;
		
		private String pspec;//HIT,NEW,BEST
		private String pcontent;
		private String pcompany;
		private java.sql.Date pindate;
		
		private int totalPrice;/*���ǸŰ� = ��ǰ�ǸŰ� *���� */
		private int totalPoint;/*������Ʈ = ����Ʈ *���� */
		
		/**�ֹ����� �Ǵ� ��ٱ��� ������ �������� ���ǸŰ��� ������Ʈ�� �����Ѵ�*/
		public void setPqty(int pqty) {
			this.pqty = pqty;
			this.totalPrice=this.saleprice*this.pqty;
			this.totalPoint=this.point*this.pqty;			
		}//--------------------------------
		/** �������� ��ȯ�ϴ� �޼���*/
		public int getPercent() {
			int percent=(price-saleprice)*100/price;
			return percent;
		}//-----------------------------------
		
		
}///////////////////////////////
