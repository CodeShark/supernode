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
package com.bitsofproof.supernode.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bitsofproof.supernode.api.Hash;
import com.bitsofproof.supernode.api.WireFormat;

@Entity
@Table (name = "head")
public class Head implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private double chainWork;
	private int height;

	private Long previousId;
	private int previousHeight;

	@Column (length = 64, nullable = false)
	private String leaf;

	public static Head fromLevelDB (byte[] data)
	{
		Head h = new Head ();
		WireFormat.Reader reader = new WireFormat.Reader (data);
		h.id = reader.readUint64 ();
		h.chainWork = Double.longBitsToDouble (reader.readUint64 ());
		h.height = (int) reader.readUint32 ();
		h.leaf = reader.readHash ().toString ();
		long pid = reader.readUint64 ();
		if ( pid != 0L )
		{
			h.previousId = pid;
		}
		h.previousHeight = (int) reader.readUint32 ();
		return h;
	}

	public byte[] toLevelDB ()
	{
		WireFormat.Writer writer = new WireFormat.Writer ();
		writer.writeUint64 (id);
		writer.writeUint64 (Double.doubleToLongBits (chainWork));
		writer.writeUint32 (height);
		writer.writeHash (new Hash (leaf));
		if ( previousId != null )
		{
			writer.writeUint64 (previousId);
		}
		else
		{
			writer.writeUint64 (0L);
		}
		writer.writeUint32 (previousHeight);
		return writer.toByteArray ();
	}

	public Long getId ()
	{
		return id;
	}

	public void setId (Long id)
	{
		this.id = id;
	}

	public double getChainWork ()
	{
		return chainWork;
	}

	public void setChainWork (double chainWork)
	{
		this.chainWork = chainWork;
	}

	public int getHeight ()
	{
		return height;
	}

	public void setHeight (int height)
	{
		this.height = height;
	}

	public String getLeaf ()
	{
		return leaf;
	}

	public void setLeaf (String leaf)
	{
		this.leaf = leaf;
	}

	public Long getPreviousId ()
	{
		return previousId;
	}

	public void setPreviousId (Long previousId)
	{
		this.previousId = previousId;
	}

	public int getPreviousHeight ()
	{
		return previousHeight;
	}

	public void setPreviousHeight (int previousHeight)
	{
		this.previousHeight = previousHeight;
	}
}
