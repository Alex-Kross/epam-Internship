package com.epam.jwd.task2.model.entity.leaf;

import com.epam.jwd.task2.model.entity.TextElement;

import java.util.Objects;

/**
 * This PunctMark contains string variable block of code starting and ending curly brace
 *
 * @author Karpuk A.U.
 * @version 1.1 20.2.2022
 * */
public class CodeBlock extends TextElement implements Cloneable{
    private StringBuilder code;

    public CodeBlock(String code) {
        this.code = new StringBuilder(code);

    }

    @Override
    public String getElementText() {
        return code.toString();
    }

    public StringBuilder getCode() {
        return code;
    }

    public void setCode(StringBuilder code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeBlock codeBlock = (CodeBlock) o;
        return Objects.equals(code, codeBlock.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code.toString();
    }

    @Override
    public CodeBlock clone() throws CloneNotSupportedException {
        return (CodeBlock) super.clone();
    }
}
