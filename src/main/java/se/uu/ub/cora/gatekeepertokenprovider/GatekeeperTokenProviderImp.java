/*
 * Copyright 2017 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.gatekeepertokenprovider;

import se.uu.ub.cora.httphandler.HttpHandler;
import se.uu.ub.cora.httphandler.HttpHandlerFactory;

public class GatekeeperTokenProviderImp implements GatekeeperTokenProvider {

	private String baseUrl;
	private HttpHandlerFactory httpHandlerFactory;

	private GatekeeperTokenProviderImp(String baseUrl, HttpHandlerFactory httpHandlerFactory) {
		this.baseUrl = baseUrl;
		this.httpHandlerFactory = httpHandlerFactory;
	}

	public static GatekeeperTokenProviderImp usingBaseUrlAndHttpHandlerFactory(String baseUrl,
			HttpHandlerFactory httpHandlerFactory) {
		return new GatekeeperTokenProviderImp(baseUrl, httpHandlerFactory);
	}

	@Override
	public AuthInfo getAuthTokenForUserInfo(UserInfo userInfo) {
		String url = baseUrl + "rest/authToken";
		HttpHandler httpHandler = httpHandlerFactory.factor(url);
		httpHandler.setRequestMethod("POST");
		return AuthInfo.withAuthTokenAndTimeToLiveInMilliseconds("", 0);
	}

}