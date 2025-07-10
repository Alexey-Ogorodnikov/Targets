package com.alexey.targets2.domain.repository

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Combines TargetReader and TargetWriter interfaces
 *    - Clients can choose to depend on specific interfaces (TargetReader/TargetWriter) or the full interface
 *    - Provides flexibility in dependency requirements
 * 
 * 2. LISKOV SUBSTITUTION PRINCIPLE (LSP):
 *    - Any implementation of TargetRepository can be substituted for TargetReader or TargetWriter
 *    - Maintains contract compatibility with both parent interfaces
 *    - Ensures type safety and polymorphism
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines the complete abstraction for Target operations
 *    - Domain layer depends on this abstraction, not concrete implementations
 *    - Enables dependency injection and testing
 */
interface TargetRepository : TargetReader, TargetWriter