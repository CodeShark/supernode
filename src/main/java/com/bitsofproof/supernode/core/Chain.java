/*
 * Copyright 2012 Tamas Blummer tamas@bitsofproof.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitsofproof.supernode.core;

import com.bitsofproof.supernode.api.ChainParameter;
import com.bitsofproof.supernode.model.Blk;

public interface Chain extends ChainParameter
{
	public Blk getGenesis ();

	public long getMagic ();

	public int getPort ();

	public byte[] getAlertKey ();

	public long getVersion ();

	public boolean isUnitTest ();
}