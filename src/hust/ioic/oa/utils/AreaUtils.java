package hust.ioic.oa.utils;

import hust.ioic.oa.domain.Area;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AreaUtils {

	public static List<Area> getAllArea(List<Area> topList) {
		List<Area> list = new ArrayList<Area>();
		walkAreaTreeList(topList, "┣", list);
		return list;
	}

	private static void walkAreaTreeList(Collection<Area> topList, String prefix, List<Area> list) {
		for (Area top : topList) {
			// 顶点
			Area copy = new Area(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy); // 把副本添加到同一个集合中

			// 子树
			walkAreaTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
}
