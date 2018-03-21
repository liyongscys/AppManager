package com.cabletech.common.util;

import java.nio.ByteBuffer;

/**
 * PackedBuffer
 * @author YuLeyuan
 *
 */
public class PackedBuffer {
	/**
	 * buffer
	 */
	private ByteBuffer buffer;
	

	/**
	 * 构造函数
	 * @param capacity 初始容量
	 */
	public PackedBuffer(int capacity) {
		buffer = ByteBuffer.allocate(capacity);
	}
	
    /**
     * Returns this buffer's capacity. </p>
     *
     * @return  The capacity of this buffer
     */
	public final int capacity() {
		return buffer.capacity();
	}

	/**
	 * @return
	 */
	public final int position() {
		return buffer.position();
	}
    /**
     * Sets this buffer's position.  If the mark is defined and larger than the
     * new position then it is discarded. </p>
     *
     * @param  newPosition
     *         The new position value; must be non-negative
     *         and no larger than the current limit
     *
     * @return  This buffer
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on <tt>newPosition</tt> do not hold
     */
	public final PackedBuffer position(int newPosition) {
		buffer.position(newPosition);
		return this;
	}
    /**
     * Returns this buffer's limit. </p>
     *
     * @return  The limit of this buffer
     */
	public final int limit() {
		return buffer.limit();
	}
    /**
     * Sets this buffer's limit.  If the position is larger than the new limit
     * then it is set to the new limit.  If the mark is defined and larger than
     * the new limit then it is discarded. </p>
     *
     * @param  newLimit
     *         The new limit value; must be non-negative
     *         and no larger than this buffer's capacity
     *
     * @return  This buffer
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on <tt>newLimit</tt> do not hold
     */
	public final PackedBuffer limit(int newLimit) {
		buffer.limit(newLimit);
		return this;
	}

	/**
	 * Sets this buffer's mark at its position. </p>
	 * 
	 * @return This buffer
	 */
	public final PackedBuffer mark() {
		buffer.mark();
		return this;
	}

	/**
	 * Resets this buffer's position to the previously-marked position.
	 * 
	 * <p>
	 * Invoking this method neither changes nor discards the mark's value.
	 * </p>
	 * 
	 * @return This buffer
	 * 
	 */
	public final PackedBuffer reset() {
		buffer.reset();
		return this;
	}

	/**
	 * Clears this buffer. The position is set to zero, the limit is set to the
	 * capacity, and the mark is discarded.
	 * 
	 * <p>
	 * Invoke this method before using a sequence of channel-read or <i>put</i>
	 * operations to fill this buffer. For example:
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * buf.clear(); // Prepare buffer for reading
	 * in.read(buf); // Read data
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * <p>
	 * This method does not actually erase the data in the buffer, but it is
	 * named as if it did because it will most often be used in situations in
	 * which that might as well be the case.
	 * </p>
	 * 
	 * @return This buffer
	 */
	public final PackedBuffer clear() {
		buffer.clear();
		return this;
	}

	/**
	 * Flips this buffer. The limit is set to the current position and then the
	 * position is set to zero. If the mark is defined then it is discarded.
	 * 
	 * <p>
	 * After a sequence of channel-read or <i>put</i> operations, invoke this
	 * method to prepare for a sequence of channel-write or relative <i>get</i>
	 * operations. For example:
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * buf.put(magic); // Prepend header
	 * in.read(buf); // Read data into rest of buffer
	 * buf.flip(); // Flip buffer
	 * out.write(buf); // Write header + data to channel
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * <p>
	 * This method is often used in conjunction with the
	 * {@link java.nio.ByteBuffer#compact compact} method when transferring data
	 * from one place to another.
	 * </p>
	 * 
	 * @return This buffer
	 */
	public final PackedBuffer flip() {
		buffer.flip();
		return this;
	}

	/**
	 * Rewinds this buffer. The position is set to zero and the mark is
	 * discarded.
	 * 
	 * <p>
	 * Invoke this method before a sequence of channel-write or <i>get</i>
	 * operations, assuming that the limit has already been set appropriately.
	 * For example:
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * out.write(buf); // Write remaining data
	 * buf.rewind(); // Rewind buffer
	 * buf.get(array); // Copy data into array
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @return This buffer
	 */
	public final PackedBuffer rewind() {
		buffer.rewind();
		return this;
	}

	/**
	 * Returns the number of elements between the current position and the
	 * limit. </p>
	 * 
	 * @return The number of elements remaining in this buffer
	 */
	public final int remaining() {
		return buffer.remaining();
	}

	/**
	 * Tells whether there are any elements between the current position and the
	 * limit. </p>
	 * 
	 * @return <tt>true</tt> if, and only if, there is at least one element
	 *         remaining in this buffer
	 */
	public final boolean hasRemaining() {
		return buffer.hasRemaining();
	}

	/**
	 * Tells whether or not this buffer is read-only. </p>
	 * 
	 * @return <tt>true</tt> if, and only if, this buffer is read-only
	 */
	public boolean isReadOnly() {
		return buffer.isReadOnly();
	}

	/**
	 * Tells whether or not this buffer is backed by an accessible array.
	 * 
	 * <p>
	 * If this method returns <tt>true</tt> then the {@link #array() array} and
	 * {@link #arrayOffset() arrayOffset} methods may safely be invoked.
	 * </p>
	 * 
	 * @return <tt>true</tt> if, and only if, this buffer is backed by an array
	 *         and is not read-only
	 * 
	 * @since 1.6
	 */
	public boolean hasArray() {
		return buffer.hasArray();
	}

	/**
	 * Returns the array that backs this buffer&nbsp;&nbsp;<i>(optional
	 * operation)</i>.
	 * 
	 * <p>
	 * This method is intended to allow array-backed buffers to be passed to
	 * native code more efficiently. Concrete subclasses provide more
	 * strongly-typed return values for this method.
	 * 
	 * <p>
	 * Modifications to this buffer's content will cause the returned array's
	 * content to be modified, and vice versa.
	 * 
	 * <p>
	 * Invoke the {@link #hasArray hasArray} method before invoking this method
	 * in order to ensure that this buffer has an accessible backing array.
	 * </p>
	 * 
	 * @return The array that backs this buffer
	 * 
	 * 
	 * @since 1.6
	 */
	public byte[] array() {
		return buffer.array();
	}

	/**
	 * Returns the offset within this buffer's backing array of the first
	 * element of the buffer&nbsp;&nbsp;<i>(optional operation)</i>.
	 * 
	 * <p>
	 * If this buffer is backed by an array then buffer position <i>p</i>
	 * corresponds to array index <i>p</i>&nbsp;+&nbsp;<tt>arrayOffset()</tt>.
	 * 
	 * <p>
	 * Invoke the {@link #hasArray hasArray} method before invoking this method
	 * in order to ensure that this buffer has an accessible backing array.
	 * </p>
	 * 
	 * @return The offset within this buffer's array of the first element of the
	 *         buffer
	 * 
	 * 
	 * @since 1.6
	 */
	public int arrayOffset() {
		return buffer.arrayOffset();
	}

}
