package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public final class ImageUtil {
	private ImageUtil() {
		// TODO Auto-generated constructor stub
	}
	public static  List<InputStream> getImages(final HttpServletRequest request,final String [] delnewimglist) throws IOException, ServletException{
		final ArrayList<Part> 	arr = (ArrayList<Part>) request.getParts();
		final Iterator<Part> iterator = arr.iterator();
		final List<InputStream> inputStreamList = new ArrayList<>();
		while (iterator.hasNext()) {
			final Part part = iterator.next();
			if ((part.getContentType() != null)&&part.getContentType().equals("image/jpeg")) {
				boolean insert = true;
				if (delnewimglist != null) {
					for (final String delnewimg : delnewimglist) {
						if (part.getSubmittedFileName().equals(delnewimg)) {
							insert = false;
						}
					}
				}
				if (insert == true) {
					inputStreamList.add(part.getInputStream());
				}

			}

		}
		return inputStreamList;
	}
}
