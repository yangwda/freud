package org.langera.freud.optional.classfile;

public abstract class AbstractInstructionVisitor implements InstructionVisitor
{
    @Override
    public void instruction(final Opcode opcode)
    {
    }

    @Override
    public void methodInvocation(final Opcode opcode, final int lineNumber, final String owner, final String methodName, final String... args)
    {
    }

    static String typeEncoding(Class clazz)
    {
        final StringBuilder sb = new StringBuilder();
        if (clazz.isArray())
        {
            sb.append(clazz.getName().replace('.', '/'));
        }
        else
        {
            if (clazz.isPrimitive())
            {
                if (boolean.class.equals(clazz))
                {
                    sb.append("Z");
                }
                else if (byte.class.equals(clazz))
                {
                    sb.append("B");
                }
                else if (char.class.equals(clazz))
                {
                    sb.append("C");
                }
                else if (double.class.equals(clazz))
                {
                    sb.append("D");
                }
                else if (float.class.equals(clazz))
                {
                    sb.append("F");
                }
                else if (int.class.equals(clazz))
                {
                    sb.append("I");
                }
                else if (long.class.equals(clazz))
                {
                    sb.append("J");
                }
                else if (short.class.equals(clazz))
                {
                    sb.append("S");
                }
            }
            else
            {
                sb.append("L").append(clazz.getName().replace('.', '/')).append(";");
            }
        }
        return sb.toString();
    }

}
