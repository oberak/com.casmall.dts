delete from ts_wgt_inf;
delete from ts_cst_mst;
delete from ts_car_mst;
delete from ts_prdt_mst;
update cm_cd set cd_nm = '' where cd_id = 'CUST_INF';
update cm_usr_inf set rgn_dt = '2013-01-01 00:00:00.0', rgn_id = 'DEFAULT', edt_dt = NULL, edt_id = NULL;
update ts_prt_inf set rgn_dt = '2013-01-01 00:00:00.0', rgn_id = 'DEFAULT', edt_dt = NULL, edt_id = NULL;
update ts_prt_attr set rgn_dt = '2013-01-01 00:00:00.0', rgn_id = 'DEFAULT', edt_dt = NULL, edt_id = NULL;