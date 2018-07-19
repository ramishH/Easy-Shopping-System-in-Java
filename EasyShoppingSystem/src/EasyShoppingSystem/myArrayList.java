/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EasyShoppingSystem;

import java.util.Arrays;

/**
 *
 * @author hiqba
 */
public class myArrayList<Type> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private int size;
    
    private transient Object[] customElementData;
    
    public myArrayList(int initCapacity){
        super();
        if(initCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
        this.customElementData = new Object[initCapacity];
    }
    
    public myArrayList(){
        super();
        this.customElementData = EMPTY_ELEMENT_DATA;
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    
    public boolean add(Type element){
        ensureCapacity(size + 1);
        customElementData[size++] = element;
        return true;
    }
    
    public void clear(){
        for (int i = 0; i < size; i++) {
            customElementData[i] = null;
        }
        size = 0;
    }
    
    public Type get(int index){
        try{
            
        
        if (index >= size){
            throw new ArrayIndexOutOfBoundsException("Array index" + index);
        }
        }catch(Exception e)
        {
//            System.out.println("Array index out of bound exception with index at " + index);
        }
        return (Type)customElementData[index];
    }
    
    public void add(int index, Type element){
        ensureCapacity(size + 1);
        System.arraycopy(customElementData, index, customElementData, index + 1, size - index);
        customElementData[index] = element;
        size++;
    }
    
    public Type remove(int index){
        Type oldValue = (Type)customElementData[index];
        int removeNum = size - index - 1;
        if (removeNum > 0){
            System.arraycopy(customElementData, index + 1, customElementData, index, removeNum);
            }
        customElementData[--size] = null;
        return oldValue;
    }
    
    private void growMyArrayList(int minCapacity){
        int oldCapacity = customElementData.length;
        int newCapacity = oldCapacity + (oldCapacity/2);
        if ((newCapacity - minCapacity) < 0 ){
            newCapacity = minCapacity;
        }
        customElementData = Arrays.copyOf(customElementData, newCapacity);
    }
    
    private void ensureCapacity(int minCapacity){
        if (customElementData == EMPTY_ELEMENT_DATA){
            minCapacity = Math.max(DEFAULT_INITIAL_CAPACITY, minCapacity);
        }
        if (minCapacity - customElementData.length > 0){
            growMyArrayList(minCapacity);
        }
    }
    
    
}
