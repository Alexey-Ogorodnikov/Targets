package com.alexey.targets2.domain.usecase.base

import kotlinx.coroutines.flow.Flow

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Base interface that can be extended without modification
 *    - New use case types can be added by implementing these interfaces
 *    - Existing code doesn't need to change when new use cases are added
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines abstraction for use case operations
 *    - Higher-level modules depend on these abstractions
 *    - Allows for different implementations and testing
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Each interface has a single responsibility
 *    - UseCase: handles one-time operations
 *    - FlowUseCase: handles reactive operations
 *    - UnitUseCase: handles operations without return values
 */
interface UseCase<in P, R> {
    suspend operator fun invoke(parameters: P): R
}

interface FlowUseCase<in P, R> {
    operator fun invoke(parameters: P): Flow<R>
}

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. LISKOV SUBSTITUTION PRINCIPLE (LSP):
 *    - UnitUseCase can be substituted for UseCase<P, Unit>
 *    - Maintains contract compatibility with parent interface
 *    - Provides type safety for operations without return values
 * 
 * 2. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Specialized interface for operations that don't return values
 *    - Clients don't need to handle return type when not needed
 */
interface UnitUseCase<in P> : UseCase<P, Unit> {
    override suspend operator fun invoke(parameters: P)
}

interface UnitFlowUseCase<in P> : FlowUseCase<P, Unit> {
    override operator fun invoke(parameters: P): Flow<Unit>
} 