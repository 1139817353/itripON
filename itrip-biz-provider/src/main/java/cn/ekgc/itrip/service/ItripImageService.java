package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.entity.ItripImage;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ItripImageService {
	/**
	 * <b>根据targetId查询酒店图片（type=0）</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<ItripImage> getImageListByQuery(ItripImage query)throws Exception;
}
