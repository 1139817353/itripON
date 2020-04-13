package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.entity.ItripImage;
import cn.ekgc.itrip.base.transport.ItripImageTransport;
import cn.ekgc.itrip.service.ItripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("itripImageTransport")
@RequestMapping("/img/trans")
public class ItripImageTransportimpl implements ItripImageTransport {
	@Autowired
	private ItripImageService itripImageService;
	/**
	 * <b>根据targetId查询酒店图片（type=0）</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/list")
	public List<ItripImage> getImageListByQuery(ItripImage query) throws Exception {
		return itripImageService.getImageListByQuery(query);
	}
}
