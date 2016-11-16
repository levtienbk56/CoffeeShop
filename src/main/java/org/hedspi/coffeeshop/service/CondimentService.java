package org.hedspi.coffeeshop.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hedspi.coffeeshop.domain.model.Condiment;
import org.hedspi.coffeeshop.mapper.CondimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CondimentService {
	private static final Logger logger = LogManager.getLogger(CondimentService.class);

	@Autowired
	private CondimentMapper condimentMapper;

	public List<Map<String, Object>> mapDataTable() {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		List<Condiment> condiments = selectAll();
		for (Condiment cd : condiments) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cd.getId());
			map.put("name", cd.getName());
			map.put("price", cd.getPrice());
			map.put("available", cd.isEnabled());
			list.add(map);
		}
		return list;
	}

	/*
	 * validate condiment, by check with data in DB
	 */
	public boolean isAvailable(Condiment condiment) {
		logger.entry(condiment);
		Condiment c = select(condiment.getId());
		if (c == null) {
			return false;
		}
		// update newest price
		condiment.setPrice(c.getPrice());
		return true;
	}

	public int insert(Condiment condiment) {
		if (validateBefore(condiment)) {
			try {
				return condimentMapper.insert(condiment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int update(Condiment condiment) {
		if (validateBefore(condiment)) {
			try {
				return condimentMapper.update(condiment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int delete(int id) {
		if (id > 0) {
			try {
				return condimentMapper.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public Condiment select(int id) {
		if (id > 0) {
			try {
				return condimentMapper.select(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Condiment> selectAll() {
		try {
			return condimentMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Condiment> selectAllActive() {
		try {
			return condimentMapper.selectAllActive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean validateBefore(Condiment condiment) {
		if (condiment != null && condiment.getName() != null && !condiment.getName().equals("")
				&& condiment.getPrice() >= 0) {
			return true;
		} else {
			return false;
		}
	}
}
