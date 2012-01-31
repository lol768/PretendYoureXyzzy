package net.socialgamer.cah.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.socialgamer.cah.Constants.AjaxOperation;
import net.socialgamer.cah.Constants.AjaxResponse;
import net.socialgamer.cah.Constants.ReturnableData;
import net.socialgamer.cah.RequestWrapper;
import net.socialgamer.cah.data.ConnectedUsers;
import net.socialgamer.cah.data.User;

import com.google.inject.Inject;


public class NamesHandler extends Handler {

  public static final String OP = AjaxOperation.NAMES.toString();

  private final ConnectedUsers users;

  @Inject
  public NamesHandler(final ConnectedUsers users) {
    this.users = users;
  }

  @Override
  public Map<ReturnableData, Object> handle(final RequestWrapper request,
      final HttpSession session) {
    final Map<ReturnableData, Object> ret = new HashMap<ReturnableData, Object>();
    // TODO once there are multiple rooms, we need which one was asked for
    final Collection<User> userList = users.getUsers();
    final List<String> names = new ArrayList<String>(userList.size());
    for (final User u : userList) {
      names.add(u.getNickname());
    }
    ret.put(AjaxResponse.NAMES, names);
    return ret;
  }
}
