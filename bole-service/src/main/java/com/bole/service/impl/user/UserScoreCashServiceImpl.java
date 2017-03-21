package com.bole.service.impl.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.BoleUtil;
import com.bole.common.Constants;
import com.bole.po.dao.user.UserScoreCashMapper;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreCash;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreCashTotalVo;
import com.bole.vo.user.UserScoreCashVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.BeanUtilsExp;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;



@Service
public class UserScoreCashServiceImpl implements UserScoreCashService {

	@Autowired
	private UserScoreCashMapper mapper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserScoreDetailService userScoreDetailService;
	
	@Override
	public int insertSelective(UserScoreCash record) {
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserScoreCash record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserScoreCash initPo() {
		UserScoreCash record = new UserScoreCash();
		
		record.setId(0L);
		record.setUserId(0L);
		record.setScoreCash(new BigDecimal(0));
		record.setStatus((short) 0);
		
		record.setAddTime(TimeStampUtil.getNowSecond());
		record.setUpdateTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public UserScoreCash selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<UserScoreCash> selectBySearchVo(UserSearchVo searchVo) {
		return mapper.selectBySearchVo(searchVo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserScoreCash> list = mapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}
	
	@Override
	public BigDecimal totalCash(UserSearchVo searchVo) {
		BigDecimal totalCash = mapper.totalCash(searchVo);
		if (totalCash == null) totalCash = new BigDecimal(0);
		return totalCash;
	}
	
	@Override
	public UserScoreCashVo getVo(UserScoreCash item) {
		UserScoreCashVo vo = new UserScoreCashVo();
		BeanUtilsExp.copyPropertiesIgnoreNull(item, vo);
		
		User u = userService.selectByPrimaryKey(vo.getUserId());
		vo.setGameId(u.getGameId());
		
		String addTimeStr = TimeStampUtil.timeStampToDateStr(vo.getAddTime() * 1000, "MM-dd HH:MM");
		vo.setAddTimeStr(addTimeStr);
		
		String cashStatusName = BoleUtil.getScoreCashStatusName(vo.getStatus());
		vo.setStatusName(cashStatusName);
		
		return vo;
	}
	
	@Override
	public UserScoreCashTotalVo getTotalVo(Long userId) {
		UserScoreCashTotalVo vo = new UserScoreCashTotalVo();
		vo.setUserId(userId);
		vo.setGameId("");
		if (userId != null && userId > 0L) {
			User u = userService.selectByPrimaryKey(vo.getUserId());
			vo.setGameId(u.getGameId());
		}
		
		
		//总返利数据.
		
		UserSearchVo totalPayBackSearchVo = new UserSearchVo();
		if (userId != null && userId > 0L)  totalPayBackSearchVo.setUserIdTo(userId);
		totalPayBackSearchVo.setScoreType(Constants.SCORE_TYPE_2);
		BigDecimal totalScore = userScoreDetailService.totalScore(totalPayBackSearchVo);
		vo.setTotalScore(totalScore);
		
		//总领取数据
		UserSearchVo totalCashSearchVo1 = new UserSearchVo();
		if (userId != null && userId > 0L) totalCashSearchVo1.setUserId(userId);
		totalCashSearchVo1.setStatus((short) 1);
		BigDecimal totalCash = this.totalCash(totalCashSearchVo1);
		vo.setTotalCash(totalCash);
		
		//领取中数据
		UserSearchVo totalCashSearchVo2 = new UserSearchVo();
		if (userId != null && userId > 0L) totalCashSearchVo2.setUserId(userId);
		totalCashSearchVo2.setStatus((short) 0);
		BigDecimal totalCashing = this.totalCash(totalCashSearchVo2);
		vo.setTotalCashing(totalCashing);
		
		//可领取的数据
		BigDecimal totalStore = totalScore.subtract(totalCash);
		totalStore = totalStore.subtract(totalCashing);
		vo.setTotalStore(totalStore);
		
		return vo;
	}
}
